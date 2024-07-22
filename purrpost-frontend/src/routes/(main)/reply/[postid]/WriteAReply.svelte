<script>
	import { postReply } from '$lib/postapi';
	import { getMyself } from '$lib/userapi';

	import { page } from '$app/stores';
	import { goto } from '$app/navigation';

	let replyContent = "";
	let currentUser = {}
	async function load() {
		currentUser = await getMyself();
		console.log(currentUser);
	}
	
	load();
</script>


<div class="flex w-full gap-4 p-2">
	<img
		class="inline-block h-9 w-9 rounded-full object-cover"
		src={currentUser.avatarUrl}
		alt="User profile picture"
	/>
	<form class="w-full flex gap-4">
	    <textarea class="w-full text-xl bg-base-300 rounded-xl p-2" placeholder="Write a reply..." required
	    bind:value={replyContent}></textarea>
	    <button class="btn btn-primary"
    	on:click|preventDefault={() => { 
    		postReply($page.params.postid, replyContent); 
    		console.log("Clicked"); 
    		goto('/home').then(() => goto($page.url.pathname)); 
    	}}>
        	Post 
    	</button>
	</form>

</div>


<style>
	textarea {
		resize: none;
	}
</style>
