import toast from 'svelte-french-toast';
import { goto } from '$app/navigation';

export async function getUser(userId) {
	try {
		const response = await fetch('http://localhost:8081/api/user/' + userId, {
			method: 'GET',
			headers: {
				'Content-Type': 'application/json',
				'Authorization': localStorage.getItem('authToken'),
			},
		});

		// Check if the response is ok (status code in the range 200-299)
		if (response.status === 200) {
			return await response.json();

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
