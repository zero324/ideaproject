package designpattern.adapter;

public class AdapterTest {


    /*
        解决接口不兼容而不能一起工作问题，非常经典的案例
        在中国，民用电都是220v交流电，但是手机锂电池用的都是5v直流电。因此，我们给手机充电时就需要使用电源适配器来进行转换。使用代码还原这个生活场景
        创建AC220类，表示220v交流电
    */
    public static void main(String[] args) {
        DC5 dc5 = new PowerAdapter(new AC220());
        dc5.outputDC5V();
    }
}