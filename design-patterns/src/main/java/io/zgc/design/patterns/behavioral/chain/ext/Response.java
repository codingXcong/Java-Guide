package io.zgc.design.patterns.behavioral.chain.ext;

import lombok.Data;

@Data
public class Response {
    String content;
    public Response(String content){
        this.content = content;
    }
}
