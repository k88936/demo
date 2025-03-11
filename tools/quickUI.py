import os
from PIL import Image

def process_images(input_dir, output_dir):
    # 确保输出目录存在
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    # 遍历输入目录中的所有文件
    for filename in os.listdir(input_dir):
        if filename.endswith('.png'):
            # 构建完整的文件路径
            input_path = os.path.join(input_dir, filename)
            output_path = os.path.join(output_dir, filename)

            # 打开图像
            with Image.open(input_path) as image:
                # 获取图像的宽度和高度
                width, height = image.size
                # 初始化不透明区域的边界
                left, top, right, bottom = width, height, 0, 0

                # 遍历图像的每个像素
                for y in range(height):
                    for x in range(width):
                        # 获取像素的RGBA值
                        r, g, b, a = image.getpixel((x, y))
                        # 如果像素不透明
                        if a != 0:
                            # 更新边界
                            left = min(left, x)
                            top = min(top, y)
                            right = max(right, x)
                            bottom = max(bottom, y)

                # 计算不透明区域的宽度和高度
                width = right - left + 1
                height = bottom - top + 1

                # 打印不透明区域的信息
                print(f">>> Image: {filename}")
#                 print(f"Bottom-left corner: ({left}, {height - bottom - 1})")
                print(f"Bottom-left corner position ratio: ({left*1920.0 / image.width}, {(height - bottom - 1) *1080.0/ image.height})")
                print(f"Width: {width}, Height: {height}")
                print(f"Width ratio: {width*1920.0 / image.width} Height ratio: {height*1080.0 / image.height}")

                # 裁剪不透明区域
                cropped_image = image.crop((left, image.height - (height - bottom - 1) - height, left + width, image.height - (height - bottom - 1)))

                # 保存裁剪后的图像
                cropped_image.save(output_path)
                print(f"Saved cropped image to {output_path}")
                print('')

# 示例用法
input_directory = 'assets_raw/stages/test'
output_directory = 'assets_gen'
process_images(input_directory, output_directory)
