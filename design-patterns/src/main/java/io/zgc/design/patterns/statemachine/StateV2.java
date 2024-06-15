package io.zgc.design.patterns.statemachine;

public enum StateV2 {


    FINISH {
        @Override
        StateV2 getNext() {
            return this;
        }
    },

    UN_SUBMIT {
        @Override
        StateV2 getNext() {
            return LEADER_CHECK;
        }
    },

    LEADER_CHECK {
        @Override
        StateV2 getNext() {
            return HR_CHECK;
        }
    },

    HR_CHECK {
        @Override
        StateV2 getNext() {
            return FINISH;
        }
    };


    abstract StateV2 getNext();

}

class Test2 {
    static StateV2 getNext(StateV2 state) {
        if (state == StateV2.UN_SUBMIT) {
            return StateV2.LEADER_CHECK;
        } else if (state == StateV2.LEADER_CHECK) {
            return StateV2.HR_CHECK;
        } else if (state == StateV2.HR_CHECK) {
            return StateV2.FINISH;
        }
        throw new IllegalArgumentException("非法状态");
    }

    public static void main(String[] args) {
        StateV2 state = StateV2.UN_SUBMIT;
        System.out.println(getNext(state));
        System.out.println(state.getNext());
    }

}