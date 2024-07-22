import toast from 'svelte-french-toast';
import { goto } from '$app/navigation';


import { writable } from 'svelte/store';
export const myUserId = writable(0);
export const myUserRole = writable("user");


export function logout() {
	localStorage.setItem('authToken', '');
	localStorage.setItem('myUserId', 0);
	myUserId.set(0);
	toast.success("Logged out");
	goto('/login');
}


type LoginInfo = {
    nameTag: string;
    password: string;
};





export async function login(loginData: LoginInfo) {
	// Define the login data
	try {
		// Make the fetch request
		const response = await fetch('http://localhost:8081/api/login', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(loginData)
		});

		// Check if the response is ok (status code in the range 200-299)
		if (response.status === 200) {
			const token = response.headers.get('Authorization');
			toast.success("Logged in");

			// Save the token to local storage
			localStorage.setItem('authToken', token);
			// console.log('Token saved to local storage:', token);

			const userInfo = await response.json();
			localStorage.setItem('myUserId', userInfo.userId);
			myUserId.set(userInfo.userId);
			console.log(userInfo);

			if (userInfo.role == "admin") {
				goto("/dashboard");
			} else {
				goto("/home");
			}

		} else if (response.status === 401) {
			toast.error("Login failed");
			console.error("FAILED:", response);
		} else {
			toast.error("????");
			console.error("FAILED:", response);
		}
	} catch (error) {
		console.error(error);
	}
}


type RegisterData = {
    name: string;
    nameTag: string;
    password: string;
    email: string;
    role: string;
    bio: string;
};


export async function register(registerData: RegisterData) {
	try {
		// Make the fetch request
		const response = await fetch('http://localhost:8081/api/register', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			},
			body: JSON.stringify(registerData)
		});

		// Check if the response is ok (status code in the range 200-299)
		if (response.status === 201) {
			toast.success("Registered!");
			goto("/login");
		} else if (response.status === 401) {
			toast.error("Register failed");
			console.error("FAILED:", response);
		} else {
			toast.error("????");
			console.error("FAILED:", response);
		}
	} catch (error) {
		console.error(error);
	}
}