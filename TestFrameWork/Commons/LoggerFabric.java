package TestFrameWork.Commons;

//TODO This is the Mock class to replace it with a real one in further(in this place only);
public class LoggerFabric {
    public static class Logger{
        public void info(String message){
            System.out.println(message);
        }
    }
    static Logger logger;

    public static Logger getLogger(){
        if (logger == null){
            logger = new Logger();
        }
        return logger;
    }
}
