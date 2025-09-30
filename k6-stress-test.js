import http from 'k6/http';
import { sleep } from 'k6';

export const options = {
    vus: 1000,
    duration: '10s',
};

export default function () {
    http.get('http://localhost:8080/');
    sleep(1);
}

/**
 * Running: k6 run k6-stress-test.js
 */
