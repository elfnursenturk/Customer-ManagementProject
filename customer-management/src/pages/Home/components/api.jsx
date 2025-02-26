import axios from "axios";

// Müşterileri listeleme
export function loadCustomers(page) {
    return axios.get("http://localhost:8080/rest/api/user/list", { params: { page, size: 6 } });
}

const API_URL = 'http://localhost:8080/rest/api/user/delete';


export const deleteCustomer = async (id) => {
    try {
        const response = await axios.delete(`${API_URL}/${id}`); // ID'yi URL'ye ekliyoruz
        return response.data;
    } catch (error) {
        console.error("Delete failed", error);
        throw new Error("An error occurred while deleting the customer");
    }
};



