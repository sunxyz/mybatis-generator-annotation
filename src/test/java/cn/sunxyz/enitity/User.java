package cn.sunxyz.enitity;

public class User {

    private String firstName;

    private Integer id;

    private Integer age;

    public String getFirstName( ) {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Integer getId( ) {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge( ) {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}