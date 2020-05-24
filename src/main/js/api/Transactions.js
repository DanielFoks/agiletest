import React from "react";


const Transactions = ({transactionsList}) => {


    let renderMessage = (transaction) => {
        const {txType, amount} = transaction;
        const color = txType === "credit" ? "red" : "white";
        return (
            <tr>
                <td>{txType}</td>
                <td>{amount}</td>
            </tr>
        );
    };

    return (
        <table border={1}>
            <tbody>
            <tr>
                <th>Type</th>
                <th>Amount</th>
            </tr>
            </tbody>
            {transactionsList.map(tx => renderMessage(tx))}
        </table>
    )
};

export default Transactions;