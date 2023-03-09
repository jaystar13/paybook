import axios from "axios";
import { API_BASE_URL, ACCESS_TOKEN } from "../constants";

const client = axios.create({
  baseURL: API_BASE_URL,
});

export const checkUsername = (username) => {
  return client.get("/user/checkUsername", {
    params: { username },
    headers: {
      Authorization: `Bearer ${localStorage.getItem(ACCESS_TOKEN)}`,
    },
  });
};

export const checkEmail = (email) => {
  return client.get("/user/checkEmail", {
    params: { email },
    headers: {
      Authorization: `Bearer ${localStorage.getItem(ACCESS_TOKEN)}`,
    },
  });
};

export const signup = (signupRequest) =>
  client.post("/auth/signup", signupRequest);

export const login = (loginRequest) =>
  client.post("/auth/signin", loginRequest);

export const currentUser = () => {
  if (!localStorage.getItem(ACCESS_TOKEN)) {
    return Promise.reject("No access token set.");
  }
  return client.get("/user/me");
};
