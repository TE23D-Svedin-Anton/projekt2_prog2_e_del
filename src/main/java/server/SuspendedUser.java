package server;

public class SuspendedUser {

    private String id;
    private String customer_id;

    public SuspendedUser(String id, String customer_id) {
        this.id = id;
        this.customer_id = customer_id;
    }

    public String getId() {
        return id;
    }

    public String getCustomerId() {
        return customer_id;
    }

    @Override
    public String toString() {
        return "SuspendedUser [id=" + id + ", userId=" + customer_id + "]";
    }
}
