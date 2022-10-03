<template>
    <header>
        <div class="l_content">
            <el-button plain icon="el-icon-menu" size="mini" @click="handleMenu"></el-button>
            <el-breadcrumb>
                <el-breadcrumb-item class="color" :to="current.path" v-if="current">{{ current.menuLabel }}
                </el-breadcrumb-item>
            </el-breadcrumb>
        </div>
        <div class="r_content">
            <el-dropdown trigger="click" szie="mini">
                <span class="el_dropdown_link">
                    <img :src="userImg" />
                </span>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item @click.native="out">退出</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>
    </header>
</template>
<script>
import { mapState } from "vuex";
export default {
    name: "CommonHeader",
    data() {
        return {
            userImg: require("../assets/img/1.jpg")
        };
    },
    methods: {
        handleMenu() {
            this.$store.commit("collapseMenu");
        },
        out() {
            if (confirm('确定要退出账号吗')) {
                this.$router.push({ name: 'login' });
            }
        }
    },
    computed: {
        ...mapState({
            current: state => state.tab.currentMenu
        })
    }
};
</script>
<style  scoped>
header {
    display: flex;
    justify-content: space-between;
    align-items: center;
    height: 100%;
}

.l_content {
    display: flex;
    align-items: center;
}

.el-button {
    margin-right: 0.9375rem;
}

img {
    width: 50px;
    border-radius: 50%;
}
</style>