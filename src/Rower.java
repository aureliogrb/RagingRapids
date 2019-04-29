
public class Rower {

    private final boolean front, back, leftFront, leftBack, rightFront, rightBack;

    /*And we have a boolean for each of the stubs on the rower
    If the stub is "male" we will have it as true, if female we
    will set it to false.

    Here is what a piece would look like:

           --- front ---
          |             |
      leftFront     RightFront
          |             |
          |             |
      leftBack     RightBack
          |             |
          -----back------

       You can only sit a rower next to a "complementary" rower

       The edges of the raft can be represented by "fake" rowers.

     */
    //Will say that ID is required
    private String ID;

    private Rower(RowerBuilder builder) {
        this.ID = builder.id;
        this.front = builder.front;
        this.back = builder.back;
        this.leftFront = builder.leftFront;
        this.leftBack = builder.leftBack;
        this.rightFront = builder.rightFront;
        this.rightBack = builder.rightBack;
    }

    public String getID() {
        return ID;
    }

    public boolean isFront() {
        return front;
    }

    public boolean isBack() {
        return back;
    }

    public boolean isLeftFront() {
        return leftFront;
    }

    public boolean isLeftBack() {
        return leftBack;
    }

    public boolean isRightFront() {
        return rightFront;
    }

    public boolean isRightBack() {
        return rightBack;
    }

    @Override
    public String toString() {
        return "Rower{" +
                "ID=" + ID +
                ", front=" + front +
                ", back=" + back +
                ", leftFront=" + leftFront +
                ", leftBack=" + leftBack +
                ", rightFront=" + rightFront +
                ", rightBack=" + rightBack +
                '}';
    }

    //Builder Class
    public static class RowerBuilder{

        private String id;

        //Defaults to False
        private boolean front = false;
        private boolean back = false;
        private boolean leftFront = false;
        private boolean leftBack = false;
        private boolean rightFront = false;
        private boolean rightBack = false;

        //Builder Constructor has the required fields
        public RowerBuilder (String id) {
            this.id =id;
        }

        //Optional fields are set with additional methods
        public RowerBuilder setFront() {
            this.front = true;
            return this;
        }

        public RowerBuilder setBack() {
            this.back = true;
            return this;
        }

        public RowerBuilder setLeftFront() {
            this.leftFront = true;
            return this;
        }

        public RowerBuilder setLeftBack() {
            this.leftBack = true;
            return this;
        }

        public RowerBuilder setRightFront() {
            this.rightFront = true;
            return this;
        }

        public RowerBuilder setRightBack() {
            this.rightBack = true;
            return this;
        }

        public Rower build() {
            return new Rower(this);
        }
    }
}
