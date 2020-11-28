package com.authentication.dao;

import com.authentication.bean.*;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationDao {

    private static String url = "jdbc:mysql://localhost:3306/bagsystem";
    private static String dbUsername = "vypa";
    private static String dbPassword = "root";

    // users
    public static final String TABLE_USERS = "users";
    public static final String COLUMN_USERS_ID = "id";
    public static final String COLUMN_USERS_FIRST_NAME = "first_name";
    public static final String COLUMN_USERS_LAST_NAME = "last_name";
    public static final String COLUMN_USERS_USERNAME = "username";
    public static final String COLUMN_USERS_PROFILE_IMAGE = "profile_img";
    public static final String COLUMN_USERS_PASSWORD = "password";
    public static final String COLUMN_USERS_DATE_CREATED = "date_created";
    public static final String COLUMN_USERS_DATE_UPDATED = "date_updated";

    // groups
    public static final String TABLE_GROUPS = "groups";
    public static final String COLUMN_GROUPS_ID = "id";
    public static final String COLUMN_GROUPS_NAME = "name";
    public static final String COLUMN_GROUPS_CREATED_BY = "created_by";
    public static final String COLUMN_GROUPS_DATE_CREATED = "date_created";
    public static final String COLUMN_GROUPS_DATE_UPDATED = "date_updated";

    // permissions
    public static final String TABLE_PERMISSIONS = "permissions";
    public static final String COLUMN_PERMISSIONS_ID = "id";
    public static final String COLUMN_PERMISSIONS_NAME = "name";
    public static final String COLUMN_PERMISSIONS_CREATED_BY = "created_by";
    public static final String COLUMN_PERMISSIONS_DATE_CREATED = "date_created";
    public static final String COLUMN_PERMISSIONS_DATE_UPDATED = "date_updated";

    // permissions to groups many to many join table
    public static final String TABLE_GROUPS_PERMISSIONS = "groups_permissions";
    public static final String COLUMN_GROUPS_PERMISSIONS_ID = "id";
    public static final String COLUMN_GROUPS_PERMISSIONS_PERMISSIONS_ID = "permission_id";
    public static final String COLUMN_GROUPS_PERMISSIONS_GROUPS_ID = "group_id";

    // assign users to groups
    public static final String TABLE_USER_GROUPS = "user_groups";
    public static final String COLUMN_USER_GROUPS_ID = "id";
    public static final String COLUMN_USER_GROUPS_USER_ID = "user_id";
    public static final String COLUMN_USER_GROUPS_GROUPS_ID = "group_id";

    // user groups view
    public static final String VIEW_USER_GROUPS = "user_groups_view";
    public static final String COLUMN_VIEW_USER_GROUPS_GROUP_ID = "group_id";
    public static final String COLUMN_VIEW_USER_GROUPS_USER_ID = "user_id";
    public static final String COLUMN_VIEW_USER_GROUPS_GROUP_NAME = "name";
    public static final String COLUMN_VIEW_USER_GROUPS_USERNAME = "username";

    // group permission view
    public static final String VIEW_GROUP_PERMISSION = "group_permission_view";
    public static final String COLUMN_VIEW_GROUP_PERMISSION_GROUP_ID = "group_id";
    public static final String COLUMN_VIEW_GROUP_PERMISSION_PERMISSION_ID = "permission_id";
    public static final String COLUMN_VIEW_GROUP_PERMISSION_GROUP_NAME = "group_name";
    public static final String COLUMN_VIEW_GROUP_PERMISSION_PERMISSION_NAME = "permission_name";

    // sort order

    public static final int ORDER_BY_NONE = 1;
    public static final int ORDER_BY_ASC = 2;
    public static final int ORDER_BY_DESC = 3;

    // queries
    public static final String CHECK_USER_NAME_EXISTS = "SELECT * FROM " + TABLE_USERS + " WHERE "
            + COLUMN_USERS_USERNAME + " = ?";
    public static final String CREATE_USER = "INSERT INTO " + TABLE_USERS + "(" + COLUMN_USERS_FIRST_NAME + ", "
            + COLUMN_USERS_LAST_NAME + ", " + COLUMN_USERS_USERNAME + ", " + COLUMN_USERS_PASSWORD
            + ") VALUES (?,?,?,?)";
    public static final String LOGIN_USER = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERS_USERNAME
            + " = ? AND " + COLUMN_USERS_PASSWORD + " = ?";
    public static final String UPDATE_USER = "UPDATE " + TABLE_USERS + " SET " + COLUMN_USERS_FIRST_NAME + " =?, "
            + COLUMN_USERS_LAST_NAME + " =? , " + COLUMN_USERS_PROFILE_IMAGE + " =? WHERE " + COLUMN_USERS_USERNAME
            + " =?";
    public static final String CREATE_PERMISSION = "INSERT INTO " + TABLE_PERMISSIONS + " (" + COLUMN_PERMISSIONS_NAME
            + ", " + COLUMN_PERMISSIONS_CREATED_BY + ") VALUES (?,?)";
    public static final String CREATE_GROUP = "INSERT INTO `" + TABLE_GROUPS + "` (" + COLUMN_GROUPS_NAME + ", "
            + COLUMN_GROUPS_CREATED_BY + ") VALUES (?,?)";
    public static final String ADD_PERMISSION_TO_GROUP = "INSERT INTO " + TABLE_GROUPS_PERMISSIONS + " ("
            + COLUMN_GROUPS_PERMISSIONS_GROUPS_ID + ", " + COLUMN_GROUPS_PERMISSIONS_PERMISSIONS_ID + ") VALUES (?,?)";
    public static final String ADD_USERS_TO_GROUPS = "INSERT INTO " + TABLE_USER_GROUPS + " ("
            + COLUMN_USER_GROUPS_USER_ID + ", " + COLUMN_USER_GROUPS_GROUPS_ID + ") VALUES (?,?)";
    public static final String CHECK_IF_GROUP_EXISTS = "SELECT * FROM `" + TABLE_GROUPS + "` WHERE "
            + COLUMN_GROUPS_NAME + " =?";
    public static final String CHECK_IF_PERMISSION_EXISTS = "SELECT * FROM " + TABLE_PERMISSIONS + " WHERE "
            + COLUMN_PERMISSIONS_NAME + " =?";
    public static final String CHECK_IF_USER_GROUPS_EXISTS = "SELECT * FROM " + TABLE_USER_GROUPS + " WHERE "
            + COLUMN_USER_GROUPS_USER_ID + " =? AND " + COLUMN_USER_GROUPS_GROUPS_ID + " = ?";
    public static final String CHECK_IF_PERMISSIONS_TO_GROUPS_EXISTS = "SELECT * FROM " + TABLE_GROUPS_PERMISSIONS
            + " WHERE " + COLUMN_GROUPS_PERMISSIONS_PERMISSIONS_ID + " =? AND " + COLUMN_GROUPS_PERMISSIONS_GROUPS_ID
            + " = ?";
    public static final String GET_USER = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERS_USERNAME + "=?";
    public static final String GET_USER_WITH_ID = "SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERS_ID + "=?";
    public static final String GET_USERS = "SELECT * FROM " + TABLE_USERS;
    public static final String GET_PERMISSIONS = "SELECT * FROM " + TABLE_PERMISSIONS;
    public static final String GET_GROUPS = "SELECT * FROM `" + TABLE_GROUPS + "`";
    public static final String GET_USER_GROUP = "SELECT * FROM " + VIEW_USER_GROUPS;
    public static final String GET_PERMISSION_GROUP = "SELECT * FROM " + VIEW_GROUP_PERMISSION;
    public static final String REMOVE_USER_FROM_GROUP = "DELETE FROM " + TABLE_USER_GROUPS + " WHERE "
            + COLUMN_USER_GROUPS_USER_ID + "=? AND " + COLUMN_USER_GROUPS_GROUPS_ID + "=?";
    public static final String REMOVE_PERMISSION_FROM_GROUP = "DELETE FROM " + TABLE_GROUPS_PERMISSIONS + " WHERE "
            + COLUMN_GROUPS_PERMISSIONS_GROUPS_ID + " =? AND " + COLUMN_GROUPS_PERMISSIONS_PERMISSIONS_ID + " =?";
    public static final String DELETE_GROUP = "DELETE FROM `" + TABLE_GROUPS + "` WHERE " + COLUMN_GROUPS_NAME + " =?";
    private Connection conn;

    public boolean open() throws SQLException, ClassNotFoundException {

        Class.forName("com.mysql.jdbc.Driver");
        try {
            conn = DriverManager.getConnection(url, dbUsername, dbPassword);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    public void close() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection.");
        }
    }

    public int createUser(User user) throws SQLException, ClassNotFoundException {
        open();
        try {
            PreparedStatement checkUsernameExists = conn.prepareStatement(CHECK_USER_NAME_EXISTS);
            checkUsernameExists.setString(1, user.getUsername());
            ResultSet results = checkUsernameExists.executeQuery();
            if (results.next()) {
                throw new SQLException("Username already exists");
            } else {
                PreparedStatement createUser = conn.prepareStatement(CREATE_USER, Statement.RETURN_GENERATED_KEYS);
                createUser.setString(1, user.getFirstName());
                createUser.setString(2, user.getLastName());
                createUser.setString(3, user.getUsername());
                createUser.setString(4, user.getPassword());

                int affectedRows = createUser.executeUpdate();
                if (affectedRows != 1) {
                    throw new SQLException("Failed to create user");
                }
                ResultSet generatedKeys = createUser.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    close();
                    if (id == 1) {
                        initDatabase();
                    }
                    return id;
                } else {
                    throw new SQLException("Couldn't get new userid");
                }
            }
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
            close();
            return -1;
        }
    }

    public boolean login(User user) {
        try {
            open();
            PreparedStatement loginUser = conn.prepareStatement(LOGIN_USER, Statement.RETURN_GENERATED_KEYS);
            loginUser.setString(1, user.getUsername());
            loginUser.setString(2, user.getPassword());

            ResultSet resultSet = loginUser.executeQuery();
            int count = 0;
            while (resultSet.next()) {
                count++;
            }
            if (count == 1) {
                close();
                return true;
            } else {
                throw new SQLException("couldn't get user");
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            close();
            return false;
        }
    }

    public boolean updateUser(User user) {
        try {
            open();
            PreparedStatement updateUser = conn.prepareStatement(UPDATE_USER);
            updateUser.setString(1, user.getFirstName());
            updateUser.setString(2, user.getLastName());
            updateUser.setString(3, user.getProfileImage());
            updateUser.setString(4, user.getUsername());
            int affectedRow = updateUser.executeUpdate();
            close();
            return affectedRow == 1;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            close();
            return false;
        }
    }

    public int createPermission(Permission permission) {
        try {
            open();
            PreparedStatement checkPermissionExists = conn.prepareStatement(CHECK_IF_PERMISSION_EXISTS,
                    Statement.RETURN_GENERATED_KEYS);
            checkPermissionExists.setString(1, permission.getName());
            ResultSet results = checkPermissionExists.executeQuery();
            if (results.next()) {
                int id = results.getInt(1);
                close();
                return id;
            } else {
                PreparedStatement createNewPermission = conn.prepareStatement(CREATE_PERMISSION,
                        Statement.RETURN_GENERATED_KEYS);
                createNewPermission.setString(1, permission.getName());
                createNewPermission.setInt(2, permission.getCreatedBy());
                int affectedRows = createNewPermission.executeUpdate();
                if (affectedRows != 1) {
                    throw new SQLException("Failed to created group");
                }
                ResultSet generatedKeys = createNewPermission.getGeneratedKeys();
                if (generatedKeys.next()) {
                    return generatedKeys.getInt(1);
                } else {
                    throw new SQLException("couldn't get id");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            close();
            return -1;
        }
    }

    public int createGroup(Group group) {
        try {
            open();
            PreparedStatement checkGroupExists = conn.prepareStatement(CHECK_IF_GROUP_EXISTS,
                    Statement.RETURN_GENERATED_KEYS);
            checkGroupExists.setString(1, group.getName());
            ResultSet results = checkGroupExists.executeQuery();

            if (results.next()) {
                int id = results.getInt(1);
                close();
                return id;
            } else {
                PreparedStatement createNewGroup = conn.prepareStatement(CREATE_GROUP, Statement.RETURN_GENERATED_KEYS);
                createNewGroup.setString(1, group.getName());
                createNewGroup.setInt(2, group.getCreatedBy());
                int affectedRows = createNewGroup.executeUpdate();
                if (affectedRows != 1) {
                    throw new SQLException("Failed to created group");
                }
                ResultSet generatedKeys = createNewGroup.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    close();
                    return id;
                } else {
                    throw new SQLException("couldn't get id");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            close();
            return -1;
        }
    }

    public int assignPermissionsToGroups(GroupPermissions groupPermissions) {
        try {
            open();
            PreparedStatement checkPermissionToGroupExists = conn
                    .prepareStatement(CHECK_IF_PERMISSIONS_TO_GROUPS_EXISTS, Statement.RETURN_GENERATED_KEYS);
            checkPermissionToGroupExists.setInt(1, groupPermissions.getPermissionId());
            checkPermissionToGroupExists.setInt(2, groupPermissions.getGroupId());
            ResultSet results = checkPermissionToGroupExists.executeQuery();
            if (results.next()) {
                int id = results.getInt(1);
                close();
                return id;
            } else {
                PreparedStatement assignPermissions = conn.prepareStatement(ADD_PERMISSION_TO_GROUP,
                        Statement.RETURN_GENERATED_KEYS);
                assignPermissions.setInt(1, groupPermissions.getGroupId());
                assignPermissions.setInt(2, groupPermissions.getPermissionId());

                int affectedRows = assignPermissions.executeUpdate();
                if (affectedRows != 1) {
                    throw new SQLException("Failed to assign new permissions to group");
                }

                ResultSet generatedKeys = assignPermissions.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    close();
                    return id;
                } else {
                    throw new SQLException("couldn't get id");
                }
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            close();
            return -1;
        }
    }

    public int assignUsersToGroups(UserGroups userGroups) {
        try {
            open();
            PreparedStatement checkUserToGroupsExists = conn.prepareStatement(CHECK_IF_USER_GROUPS_EXISTS,
                    Statement.RETURN_GENERATED_KEYS);
            checkUserToGroupsExists.setInt(1, userGroups.getUserId());
            checkUserToGroupsExists.setInt(2, userGroups.getGroupId());
            ResultSet results = checkUserToGroupsExists.executeQuery();
            if (results.next()) {
                int id = results.getInt(1);
                close();
                return id;
            } else {
                PreparedStatement assignGroups = conn.prepareStatement(ADD_USERS_TO_GROUPS,
                        Statement.RETURN_GENERATED_KEYS);
                assignGroups.setInt(1, userGroups.getUserId());
                assignGroups.setInt(2, userGroups.getGroupId());

                int affectedRows = assignGroups.executeUpdate();
                if (affectedRows != 1) {
                    throw new SQLException("Failed to assign new permissions to group");
                }

                ResultSet generatedKeys = assignGroups.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int id = generatedKeys.getInt(1);
                    close();
                    return id;
                } else {
                    throw new SQLException("couldn't get id");
                }
            }
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            close();
            return -1;
        }
    }

    public boolean deleteGroup(Group group) {
        try {
            open();
            PreparedStatement statement = conn.prepareStatement(DELETE_GROUP);
            statement.setString(1, group.getName());
            int affectedRows = statement.executeUpdate();
            close();
            return affectedRows == 1;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            close();
            return false;
        }
    }

    public boolean removeUserFromGroup(UserGroups userGroups) {
        try {
            open();
            PreparedStatement statement = conn.prepareStatement(REMOVE_USER_FROM_GROUP);
            statement.setInt(1, userGroups.getUserId());
            statement.setInt(2, userGroups.getGroupId());
            int affectedRows = statement.executeUpdate();
            close();
            return affectedRows == 1;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            return false;
        }
    }

    public boolean removePermissionFromGroup(GroupPermissions groupPermissions) {
        try {
            open();
            PreparedStatement statement = conn.prepareStatement(REMOVE_PERMISSION_FROM_GROUP);
            statement.setInt(1, groupPermissions.getGroupId());
            statement.setInt(2, groupPermissions.getPermissionId());
            int affectedRows = statement.executeUpdate();
            close();
            return affectedRows == 1;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            close();
            return false;
        }
    }

    private void initDatabase() {
        Group group = new Group();
        group.setName("admin");
        group.setCreatedBy(1);
        int groupId = this.createGroup(group);
        Permission permission = new Permission();
        permission.setName("all");
        permission.setCreatedBy(1);
        int permissionId = this.createPermission(permission);
        permission.setName("addProducts");
        permissionId = this.createPermission(permission);
        permission.setName("updateProducts");
        permissionId = this.createPermission(permission);
        permission.setName("viewProducts");
        permissionId = this.createPermission(permission);
        permission.setName("deleteProducts");
        permissionId = this.createPermission(permission);
        permission.setName("addStock");
        permissionId = this.createPermission(permission);
        permission.setName("updateStock");
        permissionId = this.createPermission(permission);
        permission.setName("viewStock");
        permissionId = this.createPermission(permission);
        permission.setName("deleteStock");
        permissionId = this.createPermission(permission);
        permission.setName("addTransaction");
        permissionId = this.createPermission(permission);
        permission.setName("updateTransaction");
        permissionId = this.createPermission(permission);
        permission.setName("viewTransaction");
        permissionId = this.createPermission(permission);
        permission.setName("deleteTransaction");
        permissionId = this.createPermission(permission);
        permission.setName("addDelete");
        permissionId = this.createPermission(permission);
        permission.setName("viewGroups");
        permissionId = this.createPermission(permission);
        permission.setName("deleteGroups");
        permissionId = this.createPermission(permission);
        permission.setName("viewPermissions");
        permissionId = this.createPermission(permission);
        permission.setName("viewUsers");
        permissionId = this.createPermission(permission);
        permission.setName("assignPermission");
        permissionId = this.createPermission(permission);
        permission.setName("assignGroups");
        permissionId = this.createPermission(permission);
        UserGroups userGroups = new UserGroups();
        userGroups.setGroupId(groupId);
        userGroups.setUserId(1);
        int user_group_id = this.assignUsersToGroups(userGroups);
        GroupPermissions groupPermissions = new GroupPermissions();
        groupPermissions.setPermissionId(permissionId);
        groupPermissions.setGroupId(groupId);
        int group_permission_id = this.assignPermissionsToGroups(groupPermissions);
    }

    public User getUser(User user) {
        try {
            open();
            PreparedStatement statement = conn.prepareStatement(GET_USER);
            statement.setString(1, user.getUsername());

            ResultSet results = statement.executeQuery();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (results.next()) {
                user.setId(results.getInt(COLUMN_USERS_ID));
                user.setFirstName(results.getString(COLUMN_USERS_FIRST_NAME));
                user.setLastName(results.getString(COLUMN_USERS_LAST_NAME));
                user.setProfileImage(results.getString(COLUMN_USERS_PROFILE_IMAGE));
                user.setDateCreated(format.parse(results.getString(COLUMN_USERS_DATE_CREATED)));
                if (results.getString(COLUMN_USERS_DATE_UPDATED) != null) {
                    user.setDateUpdated(format.parse(results.getString(COLUMN_USERS_DATE_UPDATED)));
                }
            }
            close();
            return user;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            close();
            return null;
        }
    }

    public User getUserWithId(User user) {
        try {
            open();
            PreparedStatement statement = conn.prepareStatement(GET_USER_WITH_ID);
            statement.setInt(1, user.getId());

            ResultSet results = statement.executeQuery();
            while (results.next()) {
                user.setId(results.getInt(COLUMN_USERS_ID));
                user.setUsername(results.getString(COLUMN_USERS_USERNAME));
                user.setFirstName(results.getString(COLUMN_USERS_FIRST_NAME));
                user.setLastName(results.getString(COLUMN_USERS_LAST_NAME));
                user.setProfileImage(results.getString(COLUMN_USERS_PROFILE_IMAGE));
            }
            close();
            return user;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            close();
            return null;
        }
    }

    public List<User> getUsers() {
        try {
            open();
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(GET_USERS);
            List<User> users = new ArrayList<>();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (results.next()) {
                User user = new User();
                user.setId(results.getInt(COLUMN_USERS_ID));
                user.setUsername(results.getString(COLUMN_USERS_USERNAME));
                user.setLastName(results.getString(COLUMN_USERS_LAST_NAME));
                user.setFirstName(results.getString(COLUMN_USERS_FIRST_NAME));
                user.setProfileImage(results.getString(COLUMN_USERS_PROFILE_IMAGE));
                user.setDateCreated(format.parse(results.getString(COLUMN_USERS_DATE_CREATED)));
                if (results.getString(COLUMN_USERS_DATE_UPDATED) != null) {
                    user.setDateUpdated(format.parse(results.getString(COLUMN_USERS_DATE_UPDATED)));
                }
                users.add(user);
            }
            close();
            return users;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            close();
            return null;
        }
    }

    public List<Group> getGroups() {
        try {
            open();
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(GET_GROUPS);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<Group> groups = new ArrayList<>();
            while (results.next()) {
                Group group = new Group();
                group.setId(results.getInt(COLUMN_GROUPS_ID));
                group.setCreatedBy(results.getInt(COLUMN_GROUPS_CREATED_BY));
                group.setName(results.getString(COLUMN_GROUPS_NAME));
                group.setDateCreated(format.parse(results.getString(COLUMN_GROUPS_DATE_CREATED)));
                if (results.getString(COLUMN_GROUPS_DATE_UPDATED) != null) {
                    group.setDateUpdated(format.parse(results.getString(COLUMN_GROUPS_DATE_UPDATED)));
                }
                groups.add(group);
            }
            close();
            return groups;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            close();
            return null;
        }
    }

    public List<Permission> getPermissions() {
        try {
            open();
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(GET_PERMISSIONS);
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            List<Permission> permissions = new ArrayList<>();
            while (results.next()) {
                Permission permission = new Permission();
                permission.setId(results.getInt(COLUMN_PERMISSIONS_ID));
                permission.setCreatedBy(results.getInt(COLUMN_PERMISSIONS_CREATED_BY));
                permission.setName(results.getString(COLUMN_PERMISSIONS_NAME));
                permission.setDateCreated(format.parse(results.getString(COLUMN_PERMISSIONS_DATE_CREATED)));
                if (results.getString(COLUMN_PERMISSIONS_DATE_UPDATED) != null) {
                    permission.setDateUpdated(format.parse(results.getString(COLUMN_PERMISSIONS_DATE_UPDATED)));
                }
                permissions.add(permission);
            }
            close();
            return permissions;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            close();
            return null;
        }
    }

    public List<UserGroups> getUserGroups() {
        try {
            open();
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(GET_USER_GROUP);
            List<UserGroups> userGroups = new ArrayList<>();
            while (results.next()) {
                UserGroups userGroup = new UserGroups();
                userGroup.setUserId(results.getInt(COLUMN_VIEW_USER_GROUPS_USER_ID));
                userGroup.setGroupId(results.getInt(COLUMN_VIEW_USER_GROUPS_GROUP_ID));
                userGroup.setGroupName(results.getString(COLUMN_VIEW_USER_GROUPS_GROUP_NAME));
                userGroup.setUsername(results.getString(COLUMN_VIEW_USER_GROUPS_USERNAME));
                userGroups.add(userGroup);
            }
            close();
            return userGroups;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            close();
            return null;
        }
    }

    public List<GroupPermissions> getGroupsPermission() {
        try {
            open();
            Statement statement = conn.createStatement();
            ResultSet results = statement.executeQuery(GET_PERMISSION_GROUP);
            List<GroupPermissions> groupPermissions = new ArrayList<>();
            while (results.next()) {
                GroupPermissions groupPermission = new GroupPermissions();
                groupPermission.setPermissionId(results.getInt(COLUMN_VIEW_GROUP_PERMISSION_PERMISSION_ID));
                groupPermission.setGroupId(results.getInt(COLUMN_VIEW_GROUP_PERMISSION_GROUP_ID));
                groupPermission.setGroupName(results.getString(COLUMN_VIEW_GROUP_PERMISSION_GROUP_NAME));
                groupPermission.setPermissionName(results.getString(COLUMN_VIEW_GROUP_PERMISSION_PERMISSION_NAME));
                groupPermissions.add(groupPermission);
            }
            close();
            return groupPermissions;
        } catch (Exception e) {
            System.out.println("error: " + e.getMessage());
            close();
            return null;
        }
    }
}