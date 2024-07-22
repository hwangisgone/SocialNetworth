import toast from 'svelte-french-toast';
import { goto } from '$app/navigation';

export async function getAllPosts() {
	try {
		const response = await fetch('http://localhost:8081/api/allposts', {
			method: 'GET',
			headers: {
				'Content-Type': 'application/json',
				'Authorization': localStorage.getItem('authToken'),
			},
		});

		// Check if the response is ok (status code in the range 200-299)
		if (response.status === 200) {
			return await response.json();
		} else if (response.status === 403) {
			toast.error("You are not authorized to access this");
			goto("/home");
		} else if (response.status === 401) {
			// Redirect back to login
			toast.error("Login required");
			goto("/login");
		}
	} catch (error) {
		toast.error(error);
		console.error(error);
	}
}

export async function getAllUsers() {
	try {
		const response = await fetch('http://localhost:8081/api/allusers', {
			method: 'GET',
			headers: {
				'Content-Type': 'application/json',
				'Authorization': localStorage.getItem('authToken'),
			},
		});

		// Check if the response is ok (status code in the range 200-299)
		if (response.status === 200) {
			return await response.json();
		} else if (response.status === 403) {
			toast.error("You are not authorized to access this");
			goto("/home");
		} else if (response.status === 401) {
			// Redirect back to login
			toast.error("Login required");
			goto("/login");
		}
	} catch (error) {
		toast.error(error);
		console.error(error);
	}
}

export async function deleteUser(userId) {
	try {
		const response = await fetch('http://localhost:8081/api/user/delete/' + userId, {
			method: 'DELETE',
			headers: {
				'Content-Type': 'application/json',
				'Authorization': localStorage.getItem('authToken'),
			},
		});

		// Check if the response is ok (status code in the range 200-299)
		if (response.status === 204) {
			toast.success("Deleted user successfully");
		} else if (response.status === 403) {
			toast.error("You are not authorized to access this");
			goto("/home");
		} else if (response.status === 401) {
			// Redirect back to login
			toast.error("Login required");
			goto("/login");
		} else {
			toast.error("Failed");
			console.error(response);
		}
	} catch (error) {
		toast.error(error);
		console.error(error);
	}
}