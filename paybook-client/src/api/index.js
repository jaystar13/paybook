import axios from "axios";
import { API_BASE_URL, ACCESS_TOKEN } from "../constants";

const client = axios.create({
  baseURL: API_BASE_URL,
});

export const checkUsernameAvailability = (username) => {
  client.get("/user/checkUsernameAvailability", {
    params: { username },
    headers: {
      Authorization: `Bearer ${localStorage.getItem(ACCESS_TOKEN)}`,
    },
  });
};
