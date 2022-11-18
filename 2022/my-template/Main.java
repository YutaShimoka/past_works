import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        // サンプル_01
        new Main().execMode(false).ifPresent(Main::hello);

        // サンプル_02
        new Main().execMode(false).ifPresent(exec -> exec.echo("やっほー"));
    }

    /** 実行可能であればtrue */
    private boolean isEnabled;

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    /**
     * <p>
     * Execution Modeがtrueであれば、インスタンスを保持し、メソッドの実行が可能となります.
     * </p>
     * 
     * <b>使い方:</b>
     * 
     * <pre>
     * new Main().execMode(false).ifPresent(Main::hello);
     * </pre>
     * 
     * ※上記の例ではExecution Modeがfalseの為、helloメソッドは実行されません.
     * 
     * @param isEnabled
     * @return
     */
    private Optional<Main> execMode(boolean isEnabled) {
        return Optional.of(this).map(x -> {
            x.setEnabled(isEnabled);
            return x;
        }).filter(x -> x.isEnabled());
    }

    /**
     * <p>
     * 指定した値を出力します.
     * </p>
     * 
     * ※改行なし
     * 
     * @param obj
     */
    void print(Object obj) {
        System.out.print(obj);
    }

    /**
     * <p>
     * 指定した値を出力します.
     * </p>
     * 
     * ※改行あり
     * 
     * @param obj
     */
    void println(Object obj) {
        System.out.println(obj);
    }

    // サンプル_01
    private void hello() {
        this.println("こんにちは世界");
    }

    // サンプル_02
    private void echo(String x) {
        this.println(x);
    }

}
