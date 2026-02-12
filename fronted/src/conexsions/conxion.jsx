import React from "react";
import axios from "axios";

export const con = axios.create({
  baseURL: "http://localhost:3000",
  // baseURL: 'http://localhost:8080/api'
});
