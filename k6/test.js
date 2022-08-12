import { check } from "k6";
import http from "k6/http";

export let options = {


    stages: [
        { duration: "10s", target: 2 },
        { duration: "5s", target: 2 },
        { duration: "7s", target: 0 }
    ],

};

export default function() {
    let res = http.del("http://0.0.0.0:8089/api/v1/deletePi?random_number=61");
    check(res, {
        "is status 404": (r) => r.status === 404
    });

    let res1 = http.get("http://0.0.0.0:8089/api/v1/getpi_random?input_number=20");
    check(res1, {
        "is status 200": (r) => r.status === 200
    });

    //Este creará 61 por ende, algunos los puede eliminar, por lo que sirve como prueba de integración
    let res2 = http.get("http://0.0.0.0:8089/api/v1/getpi?random_number=61");
    check(res2, {
        "is status 200": (r) => r.status === 200
    });
};