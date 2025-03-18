import json
import os
import re
from concurrent.futures import ThreadPoolExecutor
from PIL import Image

dimensions_config_file = 'assets/dimensions.json'
input_dir = 'assets_raw/stages/'
output_dir = 'assets_gen'
with open(dimensions_config_file) as f:
    dimensions = json.load(f)

# 确保输出目录存在
if not os.path.exists(output_dir):
    os.makedirs(output_dir)

animation_pattern = re.compile(r'^(.+?)_(\d+)\.png$')


def calculate_bounding_box(image):
    width, height = image.size
    left, top, right, bottom = width, height, 0, 0

    # 批量加载像素数据
    pixels = image.load()

    # 遍历图像的每个像素
    for y in range(height):
        row_has_pixel = False
        for x in range(width):
            # 获取像素的RGBA值
            r, g, b, a = pixels[x, y]
            # 如果像素不透明
            if a != 0:
                # 更新边界
                left = min(left, x)
                top = min(top, y)
                right = max(right, x)
                bottom = max(bottom, y)
                row_has_pixel = True
        if row_has_pixel:
            break

    for y in range(height - 1, -1, -1):
        row_has_pixel = False
        for x in range(width):
            # 获取像素的RGBA值
            r, g, b, a = pixels[x, y]
            # 如果像素不透明
            if a != 0:
                # 更新边界
                left = min(left, x)
                top = min(top, y)
                right = max(right, x)
                bottom = max(bottom, y)
                row_has_pixel = True
        if row_has_pixel:
            break

    for x in range(width):
        col_has_pixel = False
        for y in range(height):
            # 获取像素的RGBA值
            r, g, b, a = pixels[x, y]
            # 如果像素不透明
            if a != 0:
                # 更新边界
                left = min(left, x)
                top = min(top, y)
                right = max(right, x)
                bottom = max(bottom, y)
                col_has_pixel = True
        if col_has_pixel:
            break

    for x in range(width - 1, -1, -1):
        col_has_pixel = False
        for y in range(height):
            # 获取像素的RGBA值
            r, g, b, a = pixels[x, y]
            # 如果像素不透明
            if a != 0:
                # 更新边界
                left = min(left, x)
                top = min(top, y)
                right = max(right, x)
                bottom = max(bottom, y)
                col_has_pixel = True
        if col_has_pixel:
            break

    return left, top, right, bottom


def process_image(input_path, output_path, filename,dirname):
    # 打开图像
    with Image.open(input_path) as image:
        # 计算不透明区域的边界
        left, top, right, bottom = calculate_bounding_box(image)

    # 计算不透明区域的宽度和高度
    width = right - left + 1
    height = bottom - top + 1

    # 裁剪不透明区域
    cropped_image = image.crop((left, top, left + width, top + height))

    # 保存裁剪后的图像
    cropped_image.save(output_path)
    print(f"Saved cropped image to {output_path}")
    print('')
    match = animation_pattern.match(filename)
    if match:
        filename = match.group(1)
    else:
        filename = filename.replace('.png', '')
    item= dirname+'_'+filename


    if item not in dimensions:
        dimensions[item] = {'width': 0, 'height': 0, 'x': 0, 'y': 0}

    dimensions[item]['width'] = width * 1920.0 / image.width
    dimensions[item]['height'] = height * 1080.0 / image.height
    dimensions[item]['x'] = left * 1920.0 / image.width
    dimensions[item]['y'] = (image.height - bottom) * 1080.0 / image.height

# 遍历输入目录中的所有文件
image_tasks = []
for dirname in os.listdir(input_dir):
    for filename in os.listdir(os.path.join(input_dir, dirname)):
        if filename.endswith('.png'):
            # 构建完整的文件路径
            input_path = os.path.join(input_dir, dirname, filename)
            output_path = os.path.join(output_dir,dirname+'_'+ filename)
            image_tasks.append((input_path, output_path, filename,dirname))

# 使用多线程处理图像
with ThreadPoolExecutor() as executor:
    executor.map(lambda args: process_image(*args), image_tasks)

with open(dimensions_config_file, 'w') as f:
    json.dump(dimensions, f, indent=4)
