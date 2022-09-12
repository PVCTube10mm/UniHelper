package com.github.UniHelper.views.utils;

public enum Weekdays {

    MONDAY {
        public String getShortcut() {
            return "MON";
        }

        @Override
        public int getValue() {
            return 1;
        }
    },
    TUESDAY {
        public String getShortcut() {
            return "TUE";
        }
        @Override
        public int getValue() {
            return 2;
        }
    },
    WEDNESDAY {
        public String getShortcut() {
            return "WED";
        }
        @Override
        public int getValue() {
            return 3;
        }
    },
    THURSDAY {
        public String getShortcut() {
            return "THU";
        }
        @Override
        public int getValue() {
            return 4;
        }
    },
    FRIDAY {
        public String getShortcut() {
            return "FRI";
        }
        @Override
        public int getValue() {
            return 5;
        }
    },
    SATURDAY {
        public String getShortcut() {
            return "SAT";
        }
        @Override
        public int getValue() {
            return 6;
        }
    },
    SUNDAY {
        public String getShortcut() {
            return "SUN";
        }
        @Override
        public int getValue() {
            return 7;
        }
    };

    public abstract String getShortcut();
    public abstract int getValue();
}