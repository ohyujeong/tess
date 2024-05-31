package com.flab.tess.domain;

import com.flab.tess.util.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="transaction_id", columnDefinition = "BIGINT UNSIGNED")
    private BigInteger transactionId;

    @ManyToOne
    @JoinColumn(name="receiver_account_id", referencedColumnName = "account_id")
    private Account receiverAccountId;

    @ManyToOne
    @JoinColumn(name="sender_account_id", referencedColumnName = "account_id")
    private Account senderAccountId;

    @Column
    private BigDecimal receiverBalance;

    @Column
    private BigDecimal senderBalance;

    @Column
    private BigDecimal amount;

    @Column(name="transaction_at")
    private LocalDateTime transactionAt;

    @PrePersist
    public void transactionAt() {
        this.transactionAt = LocalDateTime.now();
    }

    // 팩토리 메소드
    public static Transaction of(BigDecimal amount, Account receiverAccount, Account senderAccount, BigDecimal receiverBalance, BigDecimal senderBalance) {
        return new Transaction(
                null, // transactionId는 자동 생성
                receiverAccount,
                senderAccount,
                receiverBalance,
                senderBalance,
                amount,
                LocalDateTime.now() // 생성 시 현재 시간으로 설정
        );
    }

}
