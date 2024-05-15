<script>
	import GoogleLogo from '$lib/google-g-logo.svg?component';
	import GithubLogo from '$lib/github-mark.svg?component';
	import PurrPostLogo from '$lib/purrpost-logo.svg?component';
</script>

<div class="flex min-h-screen items-center justify-center rounded-3xl">
	<div class="nd-background w-2/3 min-h-1/2 border border-gray-900 rounded-3xl">
		<div class="mx-4 sm:mx-24 md:mx-34 lg:mx-56 mx-auto  flex items-center space-y-4 py-16 font-semibold text-gray-500 flex-col">

			
			
			<!-- <div class="w-full mx-auto max-w-sm transform space-y-4 text-center flex items-center flex-col"> -->
				<PurrPostLogo width="60" height="60"/>

				<h1 class="text-white text-2xl">Join PurrPost today</h1>
				<!-- <button class="w-full p-2 bg-gray-50 rounded-full font-bold text-gray-900 border border-gray-700 "> <img src="your-image-url.jpg" alt="Apple Logo" class="inline-block h-6 w-6 mr-2"> Sign up with Google</button> -->
				<button class="w-full p-2 bg-gray-50 rounded-full font-bold text-gray-900 border border-gray-700 flex items-center justify-center">
					<!-- {@html GoogleLogo} -->
					<GoogleLogo width="40" height="40"/>
					Sign up with Google
				</button>
				
				<button class="w-full p-2 bg-gray-50 rounded-full font-bold text-gray-900 border border-gray-700 flex items-center justify-center">
					<GithubLogo width=35 height=35/>
					Sign up with Github
				</button>


				<div class="w-full flex items-center space-x-4">
					<hr class="w-full border-b" />
					<div class="text-sm text-gray-400">Or</div>
					<hr class="w-full border-b" />
				</div>
			<p class="text-white text-xs pt-6 pb-16">Enter your email and password</p>
			<div
				class="w-full transform border-b bg-transparent duration-300 text-sm focus-within:border-indigo-500"
			>
				<input
				type="text"
				placeholder="Email"
				class="w-full border-none bg-transparent outline-none placeholder:italic focus:outline-none text-white"
				/>
			</div>

			<div
				class="w-full transform border-b bg-transparent duration-300 text-sm focus-within:border-indigo-500"
			>
				<input
				type="password"
				placeholder="Password"
				class="w-full border-none bg-transparent outline-none placeholder:italic focus:outline-none"
				/>
			</div>
			<div
				class="w-full transform border-b bg-transparent duration-300 text-sm focus-within:border-indigo-500"
			>
				<input
				type="password"
				placeholder="Confirm Password"
				class="w-full border-none bg-transparent outline-none placeholder:italic focus:outline-none"
				/>
			</div>
			
			<button class="w-full p-2 bg-gray-50 rounded-full font-bold text-gray-900 border border-gray-700 "> Create account</button>
				<p class="text-sm">Already have an account? 
					<a class="font-semibold text-sky-700" href="./login">Sign in</a> </p>
			
			<!-- </div> -->
		</div>
	</div>
</div>

