package com.midwesttape.project.challengeapplication.service;

import com.midwesttape.project.challengeapplication.model.User;
import com.midwesttape.project.challengeapplication.model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final JdbcTemplate template;

    public User user(final Long userId) {
        try {
            return template.queryForObject(
                "select " +
                    "user.id, user.firstName, user.lastName, user.username, user.password, " +
                    "address.address_id, address.address1, address.address2, address.city, address.state, address.postal " +
                    "from User join Address on User.id = Address.user_id " +
                    "where user.id = ?",
                    new UserAddressRowMapper(),
                    userId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    public void updateUser(final User user) {
        try {
            String updateSQL = "update User set firstName = ?, lastName = ?, userName = ?, password = ? where id = ?";
            template.update(updateSQL,
                user.getFirstName(), user.getLastName(), user.getUsername(), user.getPassword(), user.getId());
        } catch (DataAccessException dae) {
            dae.printStackTrace();
        }
    }

    private static class UserAddressRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
/*  Cannot use BeanPropertyRowMapper in this manner as object specific fields need to be named using "object.field"
    notation,  and h2 database does not allow column alias to have a . character

            User user = (new BeanPropertyRowMapper<>(User.class)).mapRow(rs,rowNum);
            Address address = (new BeanPropertyRowMapper<>(Address.class)).mapRow(rs,rowNum);
            user.setAddress(address);
*/

            Address address = new Address(rs.getLong("address_id"),
                                          rs.getString("address1"),
                                          rs.getString("address2"),
                                          rs.getString("city"),
                                          rs.getString("state"),
                                          rs.getString("postal"));

            User user = new User(rs.getLong("id"),
                                 rs.getString("firstname"),
                                 rs.getString("lastname"),
                                 rs.getString("username"),
                                 rs.getString("password"),
                                 address);
            return user;
        }
    }
}
