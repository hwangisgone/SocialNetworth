<script>
	import { postFollow, deleteFollow, getFollowStatus } from '$lib/userapi';
	import { page } from '$app/stores';

	export let addedvalue = 0;

	let currentId = 0;
	let followed = false;

	$: getStatus($page.params.userid);

	async function getStatus(userId) {
		currentId = userId;
		followed = userId ? await getFollowStatus(userId) : false;
		console.log(followed);
	}
</script>

{#if followed}
	<button class="btn btn-primary btn-sm -mt-8 mr-24 px-8 border-2 rounded-3xl"
		on:click={() => { deleteFollow(currentId); followed = false; addedvalue -= 1; }}>
		Followed
	</button>
{:else}
	<button class="btn btn-outline btn-primary btn-sm -mt-8 mr-24 px-8 border-2 rounded-3xl" 
		on:click={() => { postFollow(currentId); followed = true; addedvalue += 1;}}>
		Follow
	</button>
{/if}