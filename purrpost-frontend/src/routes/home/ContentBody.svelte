<script lang='ts'>
	import Post		from './components/post/Post.svelte';
	// import CommentContainer		from './components/CommentContainer.svelte';
	// import EditProfile	from './components/EditProfileCard.svelte';
	// import ProfileCard		from './components/ProfileCard.svelte';

	export let postList = [{
		content: "Lorem ipsum dolor sit amet, consectetur",
		timePosted: "2024-06-18T01:27:33.5960633+08:00",
		timeEdited: null,
		likeCount: 0,
		replyCount: 0,
		shareCount: 0,

		// Extra
		liked: false,
		user: {}
	}];

</script>


{#each postList as post}
	<Post {post}/>
{/each}


<!-- <CommentContainer />
<ProfileCard />
<EditProfile /> -->
