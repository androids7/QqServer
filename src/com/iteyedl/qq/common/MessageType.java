/**
 * declare message type
 */

package com.iteyedl.qq.common;

public interface MessageType {
	
	String message_login_succeed = "1"; //login success
	String message_login_fail = "2"; //login fail
	String message_common_message = "3"; //normal info message
	String message_get_onlineFriend = "4"; //request to return online friends message
	String message_response_onlineFriend = "5"; //reply with online friends message
	
	

}
