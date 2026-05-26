

public class MalformedLogEntryException extends Exception{
        String line;

        public MalformedLogEntryException(String line){
            this.line=line;
        }

        @Override
        public String toString(){
            return "Error Malformed Line";
        }
    }