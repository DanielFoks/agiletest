import React from "react";


const Transactions = ({transactionsList}) => {


    let renderMessage = (transaction) => {
        const {tType, amount} = transaction;
        const color = tType === "credit" ? "red" : "white";
        return (
            <tr>
                <td>{tType}</td>
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