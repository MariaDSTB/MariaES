declare module "element-plus";
declare module "axios"

declare module "*.vue" {
  import { DefineComponent } from 'vue'
  const component: DefineComponent<{}, {}, any>
  export default component
}