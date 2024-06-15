package io.zgc.design.patterns.statemachine;

import com.google.common.collect.HashBasedTable;
import lombok.Data;

import java.util.Arrays;
import java.util.List;

enum EventV4 {

    SUBMIT,

    PASS,

    REJECT;
}

public enum StateV4 {

    REJECT,

    FINISH,

    UN_SUBMIT,

    LEADER_CHECK,

    HR_CHECK;

}

interface IStateHandle<T, R> {
    R handle(T t);
}


class LeaderPassHandle implements IStateHandle<String, String> {

    @Override
    public String handle(String s) {
        System.out.println(String.format("收到了%s", s));
        return "业务处理完了";
    }
}


@Data
class SopProcess {

    private StateV4 from;
    private StateV4 to;
    private EventV4 event;
    private IStateHandle stateHandle;

}

class SopProcessBuilder {

    private SopProcess sopProcess;

    public void setSopProcess(SopProcess sopProcess) {
        this.sopProcess = sopProcess;
    }

    public static SopProcessBuilder getInstance() {
        SopProcessBuilder sopBuilder = new SopProcessBuilder();
        sopBuilder.setSopProcess(new SopProcess());
        return sopBuilder;
    }

    public SopProcessBuilder from(StateV4 state) {
        sopProcess.setFrom(state);
        return this;
    }

    public SopProcessBuilder handle(IStateHandle stateHandle) {
        sopProcess.setStateHandle(stateHandle);
        return this;
    }

    public SopProcessBuilder to(StateV4 state) {
        sopProcess.setTo(state);
        return this;
    }

    public SopProcessBuilder event(EventV4 state) {
        sopProcess.setEvent(state);
        return this;
    }

    public SopProcess build() {
        return sopProcess;
    }
}

abstract class AbstractStateMachine {

    @Data
    static class SopExec {
        private StateV4 nextState;
        private IStateHandle stateHandle;
    }

    private HashBasedTable<StateV4, EventV4, SopExec> hashBasedTable = HashBasedTable.create();

    {
        List<SopProcess> sopProcesses = init();
        sopProcesses.forEach(item -> {
            SopExec sopExec = new SopExec();
            sopExec.setNextState(item.getTo());
            sopExec.setStateHandle(item.getStateHandle());
            hashBasedTable.put(item.getFrom(), item.getEvent(), sopExec);
        });
    }

    abstract List<SopProcess> init();

    public StateV4 getNext(StateV4 state, EventV3 event) {
        SopExec result = hashBasedTable.get(state, event);
        if (result == null) {
            throw new IllegalArgumentException("未找到状态");
        }
        return result.getNextState();
    }

    public IStateHandle getHandle(StateV4 state, EventV3 event) {
        SopExec result = hashBasedTable.get(state, event);
        if (result == null) {
            throw new IllegalArgumentException("未找到状态");
        }
        return result.getStateHandle();
    }
}

class NewStateMachine extends AbstractStateMachine {


    @Override
    List<SopProcess> init() {
        return Arrays.asList(
                SopProcessBuilder.getInstance()
                        .from(StateV4.UN_SUBMIT)
                        .event(EventV4.SUBMIT)
                        .to(StateV4.LEADER_CHECK)
                        .build(),
                SopProcessBuilder.getInstance()
                        .from(StateV4.LEADER_CHECK)
                        .event(EventV4.PASS)
                        .handle(new LeaderPassHandle())
                        .to(StateV4.HR_CHECK)
                        .build()
        );
    }

    public static void main(String[] args) {
        NewStateMachine newStateMachine = new NewStateMachine();
        StateV4 state = newStateMachine.getNext(StateV4.LEADER_CHECK, EventV3.PASS);
        System.out.println(state);
        IStateHandle<String, String> stateHandle = newStateMachine.getHandle(StateV4.LEADER_CHECK, EventV3.PASS);
        String result = stateHandle.handle("入参内容");
        System.out.println(result);
    }
}



