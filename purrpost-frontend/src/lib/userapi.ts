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

export async function getMyself() {
	try {
		const response = await fetch('http://localhost:8081/api/user/myself', {
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



export async function getFollowStatus(userId) {
	try {
		const response = await fetch('http://localhost:8081/api/user/follow/' + userId, {
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
		toast.error(error.message);
		console.error(error);
	}
}

export async function postFollow(userId) {
	try {
		const response = await fetch('http://localhost:8081/api/user/follow/' + userId, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
				'Authorization': localStorage.getItem('authToken'),
			},
		});

		// Check if the response is ok (status code in the range 200-299)
		if (response.status === 201) {
			toast.success("Followed!");
			return response.body;

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

export async function deleteFollow(userId) {
	try {
		const response = await fetch('http://localhost:8081/api/user/follow/' + userId, {
			method: 'DELETE',
			headers: {
				'Content-Type': 'application/json',
				'Authorization': localStorage.getItem('authToken'),
			},
		});

		// Check if the response is ok (status code in the range 200-299)
		if (response.status === 204) {
			return;

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