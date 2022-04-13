package ru.admin.model;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.Instant;
import java.util.Objects;

/**
 * Клиенты зала
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class FintesClient {

    /**
     * Номер абонемента
     */
    @Id
    private Integer memberShipNumber;

    /**
     * Имя клиента
     */
    @Column(nullable = false)
    private String lastName;

    /**
     * Фамилия Клиента
     */
    @Column(nullable = false)
    private String surName;

    /**
     * Отчечтво Клиента
     */
    private String midleName;

    /**
     * Номер телефона
     */
    @Column(length = 12)
    private String phone;

    /**
     * Электронная почта
     */
    @Column(length = 100)
    private String mail;

    /**
     * Дата начала действия абонемента
     */
    private Instant memberShipDateStart;

    /**
     * Дата окончания действия абонемента
     */
    private Instant memberShipDateEnd;

    /**
     * Дата создания клиента
     */
    private Instant createDate;

    /**
     * Идентификатор клиента в телеграмме
     */
    @Column(length = 100)
    private String telegramId;

    /**
     * Рабочий абонемент или нет
     */
    private Boolean isActive;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        FintesClient that = (FintesClient) o;
        return memberShipNumber != null && Objects.equals(memberShipNumber, that.memberShipNumber);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
