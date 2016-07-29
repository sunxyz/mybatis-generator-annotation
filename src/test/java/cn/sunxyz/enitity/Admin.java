package cn.sunxyz.enitity;

import java.math.BigInteger;

public class Admin {

    private BigInteger role;

    private String name;

    private BigInteger id;

    public BigInteger getRole( ) {
        return this.role;
    }

    public void setRole(BigInteger role) {
        this.role = role;
    }

    public String getName( ) {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getId( ) {
        return this.id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

}