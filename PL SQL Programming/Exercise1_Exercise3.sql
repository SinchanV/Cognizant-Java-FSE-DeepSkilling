USE BankDB;

DROP PROCEDURE IF EXISTS TransferFunds;

DELIMITER $$

CREATE PROCEDURE TransferFunds(
    IN FromAcc INT,
    IN ToAcc INT,
    IN Amount DECIMAL(10,2)
)
BEGIN
    DECLARE Bal DECIMAL(10,2);

    SELECT Balance
    INTO Bal
    FROM Accounts
    WHERE AccountID = FromAcc;

    IF Bal >= Amount THEN
        UPDATE Accounts
        SET Balance = Balance - Amount
        WHERE AccountID = FromAcc;

        UPDATE Accounts
        SET Balance = Balance + Amount
        WHERE AccountID = ToAcc;

        SELECT 'Transfer Successful' AS Message;
    ELSE
        SELECT 'Insufficient Balance' AS Message;
    END IF;
END $$

DELIMITER ;

SET SQL_SAFE_UPDATES = 0;

CALL TransferFunds(1, 2, 500);

SET SQL_SAFE_UPDATES = 1;

SELECT * FROM Accounts;