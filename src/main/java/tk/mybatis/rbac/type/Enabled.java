package tk.mybatis.rbac.type;

/**
 * @author Slience
 * @version 1.0
 */
public enum Enabled {
    disabled(0),
    enabled(1);

    private final int value;

    private Enabled(int value){
        this.value = value;
    }

    public int getValue(){
        return value;
    }


}
