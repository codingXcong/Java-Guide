package io.zgc.design.patterns.statemachine;

public enum StateV1 {

    FINISH,

    UN_SUBMIT,

    LEADER_CHECK,

    HR_CHECK;


}

class Test {
    static StateV1 getNext(StateV1 state) {
        if (state == StateV1.UN_SUBMIT) {
            return StateV1.LEADER_CHECK;
        } else if (state == StateV1.LEADER_CHECK) {
            return StateV1.HR_CHECK;
        } else if (state == StateV1.HR_CHECK) {
            return StateV1.FINISH;
        }
        throw new IllegalArgumentException("非法状态");
    }

    public static void main(String[] args) {
        StateV1 state = StateV1.UN_SUBMIT;
        System.out.println(getNext(state));
    }

}