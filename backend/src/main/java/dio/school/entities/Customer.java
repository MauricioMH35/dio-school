package dio.school.entities;

import dio.school.entities.enums.EAccessLevel;
import dio.school.entities.transfers.CustomerDTO;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Entity
@Table(name = "tb_customers")
public class Customer implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    @Column(nullable = false, length = 32) private String username;
    @Column(nullable = false, length = 32) transient private String password;
    @Column(nullable = false, length = 1) private Integer access;
    @Column(nullable = false) private LocalDate register;
    @Column(nullable = false) private Boolean active;

    public Customer() {

    }

    public Customer(String username, String password, Integer access, LocalDate register, Boolean active) {
        this.username = username;
        this.password = password;
        this.access = access;
        this.register = register;
        this.active = active;
    }

    public Customer(Long id, String username, String password, Integer access, LocalDate register,
                    Boolean active) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.access = access;
        this.register = register;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAccess() {
        return access;
    }

    public void setAccess(Integer access) {
        this.access = access;
    }

    public LocalDate getRegister() {
        return register;
    }

    public void setRegister(LocalDate register) {
        this.register = register;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public static class builder {
        private Long id;
        private String username;
        private String password;
        private Integer access;
        private LocalDate register;
        private Boolean active;

        public builder id(Long id) {
            this.id = id;
            return this;
        }

        public builder username(String username) {
            this.username = username;
            return this;
        }

        public builder password(String password) {
            this.password = password;
            return this;
        }

        public builder access(Integer access) {
            this.access = access;
            return this;
        }

        public builder register(LocalDate register) {
            this.register = register;
            return this;
        }

        public builder active(Boolean active) {
            this.active = active;
            return this;
        }

        public Customer build() {
            return new Customer(id, username, password, access, register, active);
        }
    }
    public static CustomerDTO parse(Customer target) {
        return new CustomerDTO.builder()
                .id(target.getId())
                .username(target.getUsername())
                .password(target.getPassword())
                .access(EAccessLevel.findByCodeAccess(target.getAccess()))
                .register(parseDateToString(target.getRegister()))
                .active(target.active)
                .build();
    }

    public CustomerDTO parse() {
        return new CustomerDTO.builder()
                .id(this.getId())
                .username(this.getUsername())
                .password(this.getPassword())
                .access(EAccessLevel.findByCodeAccess(this.getAccess()))
                .register(parseDateToString(this.getRegister()))
                .active(this.active)
                .build();
    }

    public static String parseDateToString(LocalDate date) {
        return date.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(id, customer.id) &&
                Objects.equals(username, customer.username) &&
                Objects.equals(password, customer.password) &&
                Objects.equals(access, customer.access) &&
                Objects.equals(register, customer.register) &&
                Objects.equals(active, customer.active);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, access, register, active);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", access=" + access +
                ", register=" + register +
                ", active=" + active +
                '}';
    }
}
