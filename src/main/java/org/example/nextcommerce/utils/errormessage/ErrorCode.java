package org.example.nextcommerce.utils.errormessage;

public enum ErrorCode {
    UnknownError(0, "Unknown Error"),
    InvalidRequestContent(10, "Invalid request content"),

    Member(100, "Member Error"),
    MemberNotFound(101, "Member not found"),
    DB(700,"Database Error"),
    DBConnectionTimeOut(701, "Database connection time out"),
    DBInsertFail(702, "Database insert failed"),
    DBTransactionFail(703, "Database transaction failed"),
    DBUpdateFail(704, "Database update failed");


    private int code;
    private String description;
    private ErrorCode(int code, String description){
        this.code = code;
        this.description = description;
    }

    public int getCode(){
        return code;
    }
    public String getDescription(){
        return description;
    }

}
