abstract class Command {
    Command() {
    }

    public abstract void ADD(String[] data);

    public abstract void LIST(int data);

    public abstract void DELETE(String[] data);

    public abstract void LISTEN(String[] data);

    public abstract void RECOMMEND(String[] data);

    public abstract void SURPRISE(String[] data);
}
