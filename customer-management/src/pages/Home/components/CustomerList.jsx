import { useCallback, useEffect, useState } from "react";
import { loadCustomers, deleteCustomer } from "./api";

export function CustomerList() {
    const [customers, setCustomers] = useState({
        content: [],
        last: false,
        first: false,
        number: 0
    });
    const [searchTerm, setSearchTerm] = useState(""); // State for the search term

    const getCustomers = useCallback(async (page) => {
        const response = await loadCustomers(page);
        setCustomers(response.data);
    }, []);

    useEffect(() => {
        getCustomers();
    }, []);

    // Silme işlemi
    const handleDelete = async (id) => {
        try {
            await deleteCustomer(id);
            alert("Customer deleted successfully!");
            getCustomers(customers.number);
        } catch (error) {
            console.error("Error deleting customer:", error);
            alert("Error while deleting customer");
        }
    };

    // Filtreleme işlemi
    const filteredCustomers = customers.content.filter((customer) =>
        customer.username.toLowerCase().includes(searchTerm.toLowerCase())
    );

    return (
        <div className="container mt-5">
            <h2 className="text-center mb-4 text-primary">Customer List</h2>

            {/* Search Input */}
            <div className="mb-4">
                <input
                    type="text"
                    className="form-control"
                    placeholder="Search by username"
                    value={searchTerm}
                    onChange={(e) => setSearchTerm(e.target.value)}
                />
            </div>

            <div className="row g-3">
                {filteredCustomers.length > 0 ? (
                    filteredCustomers.map((customer, index) => (
                        <div key={index} className="col-12 col-md-6 col-lg-4">
                            <div className="card shadow-lg border-light">
                                <div className="card-header bg-primary text-white">
                                    <h5 className="mb-0">User Name: {customer.username}</h5>
                                </div>
                                <div className="card-body">
                                    <p className="card-text">
                                        <strong>Customer Id:</strong> {customer.customer && customer.customer.id ? customer.customer.id : "N/A"}<br />
                                        <strong>First Name:</strong> {customer.customer?.firstName || "N/A"}<br />
                                        <strong>Last Name:</strong> {customer.customer?.lastName || "N/A"}<br />
                                        <strong>TCKN:</strong> {customer.customer?.tckn || "N/A"}<br />
                                        <strong>Password:</strong> {customer.customer?.password || "Encrypted"}<br />
                                        <strong>Registration Date:</strong> {customer.customer?.registrationDate ? new Date(customer.customer.registrationDate).toLocaleDateString() : "N/A"}
                                    </p>
                                    <button
                                        className="btn btn-danger"
                                        onClick={() => handleDelete(customer.userid)}
                                    >
                                        Delete
                                    </button>
                                </div>
                            </div>
                        </div>
                    ))
                ) : (
                    <p>No customer found with that username.</p>
                )}
            </div>

            <div className="d-flex justify-content-between mt-4">
                {!customers.first && (
                    <button className="btn btn-outline-primary" onClick={() => getCustomers(customers.number - 1)}>Previous</button>
                )}
                {!customers.last && (
                    <button className="btn btn-outline-primary" onClick={() => getCustomers(customers.number + 1)}>Next</button>
                )}
            </div>
        </div>
    );
}
