package mx.com.lickodev.udemy.control.commons.constants;

public class MethodPermissions {

	public static final String HAS_ANY_ROLE_USER_OR_ADMIN = "hasAnyRole('ROLE_ADMIN', 'ROLE_USER')";
	public static final String HAS_ROLE_ADMIN = "hasRole('ROLE_ADMIN')";
	public static final String HAS_ROLE_USER = "hasRole('ROLE_USER')";

	private MethodPermissions() {

	}

}
