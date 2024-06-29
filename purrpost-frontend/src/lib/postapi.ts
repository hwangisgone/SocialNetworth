import toast from 'svelte-french-toast';
import { goto } from '$app/navigation';

export async function getHotPosts() {
	try {
		const response = await fetch('http://localhost:8081/api/newsfeed', {
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

export async function searchPosts(searchTerm) {
	try {
		const response = await fetch('http://localhost:8081/api/search?term=' + searchTerm, {
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

export async function getAllUserPosts(userId) {
	try {
		const response = await fetch('http://localhost:8081/api/user/' + userId + '/allposts', {
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


export async function getPost(postId) {
	try {
		const response = await fetch('http://localhost:8081/api/post/' + postId, {
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

export async function writePost(postContent) {
    try {
        const response = await fetch('http://localhost:8081/api/post', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': localStorage.getItem('authToken'),
            },
            body: JSON.stringify({ "content": postContent })
        });

        // Check if the response is ok (status code in the range 200-299)
        if (response.status === 201) {
            toast.success("Posted!");
        } else {
            toast.error("Failed");
            console.error(response);
        }
    } catch (error) {
        toast.error(error);
        console.error(error);
    }
}



export async function getPostReplies(postId) {
	try {
		const response = await fetch('http://localhost:8081/api/replies_to/' + postId, {
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

export async function postReply(postId, postContent) {
	try {
		const response = await fetch('http://localhost:8081/api/replies_to/' + postId, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
				'Authorization': localStorage.getItem('authToken'),
			},
			body: JSON.stringify({ "content": postContent })
		});

		// Check if the response is ok (status code in the range 200-299)
		if (response.status === 200) {
			toast.success("Posted");
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



export async function postReaction(postId, reactionType) {
	try {
		if (postId < 1) { throw new Error("postId < 1: " + postId); }

		const response = await fetch('http://localhost:8081/api/post/' + postId + '/react', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
				'Authorization': localStorage.getItem('authToken'),
			},
			body: JSON.stringify({ "reactionType": reactionType })
		});

		// Check if the response is ok (status code in the range 200-299)
		if (response.status === 201) {
			return;
			// console.log(userList);
		} else {
			toast.error("Failed");
			console.error(response);
		}
	} catch (error) {
		toast.error(error);
		console.error(error);
	}
}

export async function deleteReaction(postId) {
	try {
		const response = await fetch('http://localhost:8081/api/post/' + postId + '/react', {
			method: 'DELETE',
			headers: {
				'Content-Type': 'application/json',
				'Authorization': localStorage.getItem('authToken'),
			},
		});

		// Check if the response is ok (status code in the range 200-299)
		if (response.status === 204) {
			return;
			// console.log(userList);
		} else {
			toast.error("Failed");
			console.error(response);
		}
	} catch (error) {
		toast.error(error);
		console.error(error);
	}
}