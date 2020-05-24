import React, {useState} from 'react';
import SockJsClient from 'react-stomp';
import Transactions from "./Transactions";

const SOCKET_URL = 'http://localhost:8080/ws-chat/';


const MainApp = () => {

   const [transactionsList, setTransactionsList] = useState([]);

    let onConnected = () => {
        console.log("Connected!!")
    }

    let onMessageReceived = (tx) => {
        console.log('New Message Received!!', tx);
        //setTransactionsList(transactionsList.concat(tx));
    }


    return (
        <div>
            <SockJsClient
                url={SOCKET_URL}
                topics={['/topic/txTopic']}
                onConnect={onConnected}
                onDisconnect={console.log("Disconnected!")}
                onMessage={msg => onMessageReceived(msg)}
                debug={false}
            />
            <Transactions
                transactionsList={transactionsList}
            />
        </div>
    )

}



export default MainApp;