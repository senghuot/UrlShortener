package senghout.github.com.webserver;

public class Zoo {
    public String Id;
    public long low;
    public long high;


    @Override
    public String toString() {
        return "Zoo{" +
                "Id='" + Id + '\'' +
                ", low=" + low +
                ", high=" + high +
                '}';
    }
}