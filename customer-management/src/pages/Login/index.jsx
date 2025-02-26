import { useState } from "react";
import axios from "axios";

export function Login() {
    const [username, setUsername] = useState("");
    const [password, setPassword] = useState("");
    const [errors, setErrors] = useState({});
    const [apiProgress, setApiProgress] = useState(false);
    const [generalError, setGeneralError] = useState("");
    const [successMessage, setSuccessMessage] = useState("");

    const onSubmit = (event) => {
        event.preventDefault();
        setErrors({});
        setSuccessMessage("");

        if (apiProgress) return;

        setApiProgress(true);

        axios.post(
            "http://localhost:8080/rest/api/user/login",
            { username, password },
            { headers: { "Content-Type": "application/json" } }
        )
            .then((response) => {
                console.log("Başarılı:", response.data);
                setSuccessMessage("Login successful!");


            })
            .catch((error) => {
                if (error.response) {
                    if (error.response.data.validationErrors) {
                        setErrors(error.response.data.validationErrors);
                    } else {
                        setGeneralError("Invalid credentials or an error occurred.");
                    }
                    console.error("Backend Hata Yanıtı:", error.response.data);
                }
            })
            .finally(() => {
                setApiProgress(false);
            });
    };

    return (
        <div className="container">
            <div className="col-lg-6 offset-lg-3 col-sm-8 o">
                <form className="card" onSubmit={onSubmit}>
                    <div className="text-center card-header">
                        <h1>Login</h1>
                    </div>
                    <div className="card-body">
                        <div className="mb-3">
                            <label htmlFor="username" className="form-label">Username: </label>
                            <input className="form-control"
                                id="username"
                                type="text"
                                value={username}
                                onChange={(event) => setUsername(event.target.value)}
                            />
                            {errors.username && <div style={{ color: "red", fontSize: "12px" }}>{errors.username}</div>}
                        </div>

                        <div className="mb-3">
                            <label htmlFor="password" className="form-label">Password: </label>
                            <input className="form-control"
                                id="password"
                                type="password"
                                value={password}
                                onChange={(event) => setPassword(event.target.value)}
                            />
                            {errors.password && <div style={{ color: "red", fontSize: "12px" }}>{errors.password}</div>}
                        </div>

                        {successMessage && <div style={{ color: "green", backgroundColor: "#d4edda", padding: "10px", borderRadius: "5px" }}>{successMessage}</div>}
                        {generalError && <div className="alert alert-danger">{generalError}</div>}

                        <div className="text-center">
                            <button className="btn btn-outline-primary" type="submit" disabled={apiProgress}>
                                {apiProgress ? "Logging in..." : "Login"}
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    );
}
