package designpattern.templatemethod;

public class InterviewTest {
    public static void main(String[] args) {
        // 面试Java工程师
        Interview interviewee1 = new Interviewee1();
        interviewee1.process();

        // 面试前端工程师
        Interview interviewee2 = new Interviewee2();
        interviewee2.process();
    }
}