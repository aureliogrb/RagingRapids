
public class Rower {



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

    //the rest are "optional" with default being false/concave.

    private final boolean front,  leftFront, leftBack, back, rightBack, rightFront;

    private Rower(RowerBuilder builder) {

        this.ID = builder.id;

        this.front = builder.front;

        this.leftFront = builder.leftFront;
        this.leftBack = builder.leftBack;

        this.back = builder.back;

        this.rightBack = builder.rightBack;
        this.rightFront = builder.rightFront;
    }

    public String getID() {
        return ID;
    }

    public boolean isFront() {
        return front;
    }

    public boolean isLeftFront() {
        return leftFront;
    }

    public boolean isLeftBack() {
        return leftBack;
    }

    public boolean isBack() {
        return back;
    }

    public boolean isRightBack() {
        return rightBack;
    }

    public boolean isRightFront() {
        return rightFront;
    }


    @Override
    public String toString() {

        //List them counter clockwise so we can check if we wrote it right easily
        //by feeling the edges will looking at the output of "toString".

        return "Rower{" +
                "ID=" + ID +
                ", front=" + front +
                ", leftFront=" + leftFront +
                ", leftBack=" + leftBack +
                ", back=" + back +
                ", rightBack=" + rightBack +
                ", rightFront=" + rightFront +
                '}';
    }

    //Builder Class
    // as explained here https://www.journaldev.com/1425/builder-design-pattern-in-java

    public static class RowerBuilder {

        private String id;

        //Defaults to False
        private boolean front = false;

        private boolean leftFront = false;
        private boolean leftBack = false;

        private boolean back = false;

        private boolean rightBack = false;
        private boolean rightFront = false;


        //Builder Constructor has the required fields
        public RowerBuilder(String id) {
            this.id = id;
        }

        //Optional fields are set with additional methods
        public RowerBuilder setFront() {
            this.front = true;
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

        public RowerBuilder setBack() {
            this.back = true;
            return this;
        }

        public RowerBuilder setRightBack() {
            this.rightBack = true;
            return this;
        }

        public RowerBuilder setRightFront() {
            this.rightFront = true;
            return this;
        }


        public Rower build() {
            return new Rower(this);
        }
    }
}
