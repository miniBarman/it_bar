<template>
	<v-row justify="center">
		<v-dialog v-model="dialog" persistent max-width="600px">
			<template v-slot:activator="{ on }">
				<v-btn color="primary" dark v-on="on">Login</v-btn>
			</template>
			<v-card>
				<v-card-title>
					<span class="headline">Login</span>
				</v-card-title>
				<v-card-text>
					<v-form>
						<v-text-field
                            label="Login"
                            name="login"
                            prepend-icon="person"
                            type="text"
                            >
						</v-text-field>

						<v-text-field
                            id="password"
                            label="Password"
                            name="password"
                            prepend-icon="lock"
                            type="password"
                            >
						</v-text-field>
					</v-form>
				</v-card-text>
				<v-card-actions>
					<div class="flex-grow-1"/>
					<v-btn color="blue darken-1" text @click="dialog = false">Close</v-btn>
					<v-btn color="blue darken-1" text type="submit">Login</v-btn>
				</v-card-actions>
			</v-card>
		</v-dialog>
	</v-row>
</template>

<script>
    export default {
        data() {
            return{
                dialog: false,
            }
        },
        methods: {
            submit () {
                this.$v.$touch()
            }
        }
    }
</script>

<style>
</style>