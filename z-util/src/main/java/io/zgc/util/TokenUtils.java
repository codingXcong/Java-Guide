package io.zgc.util;

import java.nio.ByteBuffer;
import java.security.MessageDigest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.HmacUtils;
import org.apache.commons.lang3.StringUtils;
/**
 * token封装
 *
 */
public class TokenUtils {
	
	// token 版本号
	public static short version = 1;
	
	// token 版本号字节长度
	private static final int VERSION_BYTES = 2;
	
	// 算法字节长度
	private static final int ALGORITHM_BYTES = 1;
	
	// token 类型字节长度
	private static final int TOKEN_TYPE_BYTES = 1;
	
	// 创建时间 字节长度
	private static final int TIME_BYTES = 8;
	
	// 有效时长字节长度
	private static final int EXPIRE_BYTES = 8;
	
	// id长度标识字节长度(该长度(short)字节里面记录的值为后面有多少长度是记录的id内容)
	private static final int ID_LENGTH_BYTES = 2;
	
	// 自定义数据体长度标识字节长度(该长度(int)字节里的值为后续定义内容占多少字节)
	private static final int DATA_LENGTH_BYTES = 4;
	
	/**
	 * 生成对外token
	 * @param algorithm 算法类型
	 * @param type token类型
	 * @param createTime 创建时间(当小于等于0将自动设置为系统当前时间)
	 * @param ttlMillis 有效时长
	 * @param id token唯一标识，不能为空（如可以用uuid来映射到缓存或后天存储）
	 * @param data 自定义数据体，可为空
	 * @param secret 签名秘钥
	 * @return 64进制字符串token
	 */
	public static String encodeToken(byte algorithm, byte type, long createTime
			,long ttlMillis, byte[] id, byte[] data, byte[] secret){
		
		if ( AlgorithmType.HS256.getCode() != algorithm
				|| id == null || id.length == 0 || id.length > Short.MAX_VALUE
				|| secret==null ||secret.length == 0) {
			throw new IllegalArgumentException();
		}
		
		createTime = createTime <= 0 ? System.currentTimeMillis() : createTime;
		
		short idLength = (short)id.length;
		
		int dataLength = data==null?0:data.length;
		
		int contentLength = VERSION_BYTES 
				+ ALGORITHM_BYTES
				+ TOKEN_TYPE_BYTES
				+ TIME_BYTES
				+ EXPIRE_BYTES
				+ ID_LENGTH_BYTES 
				+ idLength
				+ DATA_LENGTH_BYTES
				+ dataLength;
			
		int signLength = AlgorithmType.HS256.getLength();
					
		ByteBuffer buffer = ByteBuffer.allocate(contentLength+signLength);
		buffer.putShort(version);
		buffer.put(algorithm);
		buffer.put(type);
		buffer.putLong(createTime);
		buffer.putLong(ttlMillis);
		buffer.putShort(idLength);
		buffer.put(id);
		buffer.putInt(dataLength);
		if (dataLength > 0) {
			buffer.put(data);
		}

		buffer.flip();
		byte[] contentData = new byte[contentLength];
		buffer.get(contentData);
		byte[] signPart = HmacUtils.hmacSha256(secret, contentData);
		
		buffer.limit(buffer.capacity());
		
		buffer.put(signPart);
		
		buffer.flip();
		
		
		return Base64.encodeBase64URLSafeString(buffer.array());
	}
	
	/**
	 * 解码并验证token签名
	 * @param token 64进制token字符串
	 * @param secret 签名密钥
	 * @return
	 */
	public static TokenInfo decodeToken(String token, byte[] secret){
		
		if (StringUtils.isBlank(token) || secret == null || secret.length == 0) {
			throw new IllegalArgumentException();
		}

		byte[] totalData = Base64.decodeBase64(token);
		
		ByteBuffer buffer = ByteBuffer.wrap(totalData);
		
		short version = buffer.getShort();
		byte algorithm = buffer.get();
		byte type = buffer.get();
		
		if (AlgorithmType.HS256.getCode() != algorithm
				|| TokenType.BEARER.getCode() != type) {
			new IllegalArgumentException("not support algorithm or type");
		}
		
		long createTime = buffer.getLong();
		long ttlMillis = buffer.getLong();
		short idLength = buffer.getShort();
		byte[] id = new byte[idLength];
		buffer.get(id);
		int dataLength = buffer.getInt();
		byte[] data = null;
		if (dataLength > 0) {
			data = new byte[dataLength];
			buffer.get(data);
		}
		
		
		int contentLength = VERSION_BYTES 
				+ ALGORITHM_BYTES
				+ TOKEN_TYPE_BYTES
				+ TIME_BYTES
				+ EXPIRE_BYTES
				+ ID_LENGTH_BYTES 
				+ idLength
				+ DATA_LENGTH_BYTES
				+ dataLength;
			
		int signLength = AlgorithmType.HS256.getLength();
		
		byte[] signData = new byte[signLength];
		
		buffer.get(signData);
		
		buffer.position(0);
		buffer.limit(contentLength);
		byte[] contentData = new byte[contentLength];
		buffer.get(contentData);
		
		byte[] signVali = HmacUtils.hmacSha256(secret,contentData);
		
		if (!MessageDigest.isEqual(signData, signVali)) {
			throw new TokenSignatureException("invalid signature");
		}
		
		if (ttlMillis > 0 && System.currentTimeMillis() > createTime+ttlMillis) {
			throw new TokenExpireException("token expire");
		}
		
		TokenInfo ti = new TokenInfo();
		ti.version = version;
		ti.algorithm = algorithm;
		ti.type = type;
		ti.createTime = createTime;
		ti.expire = ttlMillis;
		ti.id = id;
		ti.data =data;
		return ti;
		
	}
	
	@SuppressWarnings("serial")
	public static class TokenSignatureException extends IllegalArgumentException {

		public TokenSignatureException() {
			super();
		}

		public TokenSignatureException(String message, Throwable cause) {
			super(message, cause);
		}

		public TokenSignatureException(String s) {
			super(s);
		}

		public TokenSignatureException(Throwable cause) {
			super(cause);
		}
		
	}
	
	@SuppressWarnings("serial")
	public static class TokenExpireException extends IllegalArgumentException {

		public TokenExpireException() {
			super();
		}

		public TokenExpireException(String message, Throwable cause) {
			super(message, cause);
		}

		public TokenExpireException(String s) {
			super(s);
		}

		public TokenExpireException(Throwable cause) {
			super(cause);
		}
		
	}
	
	/**
	 * token详情
	 *
	 */
	public static class TokenInfo {
		private short version;
		private byte algorithm;
		private byte type;
		private long createTime;
		private long expire;
		private byte[] id;
		private byte[] data;
		/**
		 * token版本号
		 * @return
		 */
		public short getVersion() {
			return version;
		}
		/**
		 * 算法类型
		 * @return
		 */
		public byte getAlgorithm() {
			return algorithm;
		}
		/**
		 * token类型
		 * @return
		 */
		public byte getType() {
			return type;
		}
		/**
		 * 创建时间
		 * @return
		 */
		public long getCreateTime() {
			return createTime;
		}
		/**
		 * 有效时长
		 * @return
		 */
		public long getExpire() {
			return expire;
		}
		/**
		 * token唯一标识 id
		 * @return
		 */
		public byte[] getId() {
			return id;
		}
		/**
		 * token携带的自定义数据
		 * @return
		 */
		public byte[] getData() {
			return data;
		}
		
	}
	
}