import Axios from "axios";

const api = Axios.create({
    baseURL: '/api/',
});

const txApi = {
    getMessages: (groupId) => {
        console.log('Calling get messages from API');
        return api.get(`messages/${groupId}`);
    }
}

export default txApi;