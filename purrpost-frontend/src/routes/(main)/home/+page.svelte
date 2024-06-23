<script lang='ts'>
	// import EyeIcon		from '$lib/icon/eye.svg?component';
	// import HeartIcon	from '$lib/icon/heart.svg?component';
	// import ReplyIcon	from '$lib/icon/talk-bubbles.svg?component';
	// import ShareIcon	from '$lib/icon/share.svg?component';

	import ContentBody  from './ContentBody.svelte';


	import toast from 'svelte-french-toast';
	import { goto } from '$app/navigation';

	let postList = [];
	let userMap = {};
	
	import { myReactionMap } from '$lib/stores';

async function getAllPosts() {
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
			postList = await response.json();
			console.log(postList);
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

async function getAllUsers() {
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
			const userList = await response.json();

			userMap = userList.reduce((map, user) => {
				map[user.userId] = user;
				return map;
			}, {});

			// console.log(userList);
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

async function getAllMyReactions() {
	try {
		const response = await fetch('http://localhost:8081/api/myreactions', {
			method: 'GET',
			headers: {
				'Content-Type': 'application/json',
				'Authorization': localStorage.getItem('authToken'),
			},
		});

		// Check if the response is ok (status code in the range 200-299)
		if (response.status === 200) {
			const reactionList = await response.json();

			$myReactionMap = reactionList.reduce((map, reaction) => {
				map[reaction.postId] = reaction;
				return map;
			}, {});

			// console.log(userList);
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

let dataLoaded = false;
async function loadData() {
	await getAllPosts();
	await getAllUsers();
	await getAllMyReactions();

	console.log($myReactionMap);

	postList.forEach(post => {
		post.liked = $myReactionMap[post.id] ? true : false;
		post.user = userMap[post.userId];
	});

	postList = postList.sort((a, b) => a.timePosted.localeCompare(b.timePosted));

	dataLoaded = true;
}

	loadData();



</script>

<!-- Main -->
<main class="max-w-full h-full flex relative overflow-y-hidden">
  <!-- Container -->
	<div id="imageContainer" class="w-full m-4 flex flex-col  items-start bg-opacity-0 justify-start gap-4 overflow-y-scroll">
	<!-- Container -->
	{#if dataLoaded}
		<ContentBody {postList}/>
	{:else}
		<p>Fetching...</p>
	{/if}

	</div>
</main>
	  