package io.zgc.design.patterns.statemachine;

import com.google.common.collect.HashBasedTable;

enum EventV3 {

    SUBMIT,

    PASS,

    REJECT;
}

public enum StateV3 {

    REJECT,

    FINISH,

    UN_SUBMIT,

    LEADER_CHECK,

    HR_CHECK ;



}

class StateMachine {

    private static HashBasedTable<StateV3, EventV3, StateV3> hashBasedTable = HashBasedTable.create();


    static {
        hashBasedTable.put(StateV3.UN_SUBMIT, EventV3.SUBMIT, StateV3.LEADER_CHECK);
        hashBasedTable.put(StateV3.LEADER_CHECK, EventV3.PASS, StateV3.HR_CHECK);
        hashBasedTable.put(StateV3.UN_SUBMIT, EventV3.REJECT, StateV3.REJECT);
        hashBasedTable.put(StateV3.HR_CHECK, EventV3.PASS, StateV3.FINISH);
        hashBasedTable.put(StateV3.UN_SUBMIT, EventV3.REJECT, StateV3.REJECT);
    }


    public static StateV3 getNext(StateV3 state, EventV3 event) {
        StateV3 result = hashBasedTable.get(state, event);
        if (result == null) {
            throw new IllegalArgumentException("未找到状态");
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(StateMachine.getNext(StateV3.LEADER_CHECK, EventV3.PASS));
    }

}


