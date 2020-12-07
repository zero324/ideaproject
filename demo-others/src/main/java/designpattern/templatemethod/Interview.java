package designpattern.templatemethod;

/**
 * 面试大厂流程类
 */
public abstract class Interview {


    private final void register() {
        System.out.println("面试登记");
    }
    protected abstract void communicate();

    private final void notifyResult() {
        System.out.println("HR小姐姐通知面试结果");
    }
    protected final void  process() {
        // step1：面试登记
        this.register();
        // step2：面试交流
        this.communicate();
        // step3：通知结果
        this.notifyResult();
    }
}


