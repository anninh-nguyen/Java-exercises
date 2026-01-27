package Core;

public abstract class MasterObject {
    
    public MasterObject() {
        // Constructor
    }

    protected abstract int insert();
    protected abstract MasterObject select();
    protected abstract boolean update();
    protected abstract boolean delete ();
}
